const express = require('express');                 //express 추가
const mysql = require('mysql2');                     //mysql 모듈 추가
const cors = require('cors');                       // CORS 패키지 import (프록시 서버 설정)
const Pagination = require('./utils/Pagination');   // 페이징 처리 js 임포트
const app = express();
const port = 5000;          //express 서버 포트 설정

// 미들웨어 설정 (JSON 파싱)
app.use(cors()); // CORS 미들웨어 추가 --> 크로스오버 오류 방지 (작업폴더에 cors 설치해야함 => 터미널에서 npm install cors)
app.use(express.json()); //json 파싱 모듈

// MariaDB 연결 설정
const db = mysql.createConnection({
  host: 'estsw-rds-mariadb-edu.che0sy2qqx6s.ap-northeast-2.rds.amazonaws.com',
  user: 'admin',
  password: 'admin123!!',
  port: 3306,
  database: 'est_eep_db'
});

// MariaDB 연결
db.connect(err => {
  if (err) {
    console.error('Error connecting to MariaDB: ', err);
    return;
  }
  console.log('Connected to MariaDB ==> MariaDB 연결 성공');
});

// GET /board - 목록 조회
app.get('/board', (req, res) => {
    const page = parseInt(req.query.page) || 1;
    const size = parseInt(req.query.size) || 10;
    const sk = req.query.sk || '';
    const sv = req.query.sv || '';
    //전체카운트  선 조회
    let queryCount = `
      SELECT COUNT(IDX) AS totalListCnt
      FROM TB_BOARD
      WHERE 1=1
    `;
  
    if (sk && sv) {
      queryCount += ` AND ${sk.toUpperCase()} LIKE '%${sv}%'`;
    }

    db.query(queryCount, (err, countResults) => {
      if (err) return res.status(500).json({ error: err.message });
        
      const totalListCnt = countResults[0].totalListCnt;
      const pagination = new Pagination(totalListCnt, page, size, 5);
  
      let query = `
        SELECT IDX, TITLE, CONTENTS, CREATED_BY AS createdBy, CREATED_AT AS createdAt
        FROM TB_BOARD
        WHERE 1=1
      `;
  
      if (sk && sv) {
        query += ` AND ${sk.toUpperCase()} LIKE '%${sv}%'`;
      }
  
      query += ` ORDER BY IDX DESC LIMIT ${pagination.startIndex}, ${pagination.pageSize}`;
  
      db.query(query, (err, results) => {
        if (err) return res.status(500).json({ error: err.message });
        
        //값 변환 (스프링부트_SampleController 등.. 과 값 맞추기)
        const transformedResults = results.map(item => ({
            idx: item.IDX,
            title: item.TITLE,
            contents: item.CONTENTS,
            createdBy: item.createdBy,
            createdAt: item.createdAt,
        }));
        res.json({ pagination, data: transformedResults });
      });
    });
  });
  
  // GET /board/total - 총 게시물 수 조회
  app.get('/board/total', (req, res) => {
    const sk = req.query.sk || '';
    const sv = req.query.sv || '';
  
    let query = `
      SELECT COUNT(IDX) AS totalCount
      FROM TB_BOARD
      WHERE 1=1
    `;
  
    if (sk && sv) {
      query += ` AND ${sk.toUpperCase()} LIKE '%${sv}%'`;
    }
  
    db.query(query, (err, results) => {
      if (err) {
        return res.status(500).json({ error: err.message });
      }
      res.json(results[0]);
    });
  });
  

// GET /board/total - 총 게시물 수 조회
app.get('/board/total', (req, res) => {
  const sk = req.query.sk || '';
  const sv = req.query.sv || '';

  let query = `
    SELECT COUNT(IDX) AS totalCount
    FROM TB_BOARD
    WHERE 1=1
  `;

  if (sk && sv) {
    if (sk === 'title') {
      query += ` AND TITLE LIKE '%${sv}%'`;
    } else if (sk === 'contents') {
      query += ` AND CONTENTS LIKE '%${sv}%'`;
    } else if (sk === 'createdBy') {
      query += ` AND CREATED_BY LIKE '%${sv}%'`;
    }
  }

  db.query(query, (err, results) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.json(results[0]);
  });
});

// GET /board/:idx - 데이터 1개 조회
app.get('/board/:idx', (req, res) => {
  const idx = req.params.idx;

  const query = 'SELECT IDX, TITLE, CONTENTS, CREATED_BY AS createdBy, CREATED_AT AS createdAt FROM TB_BOARD WHERE IDX = ?';
  db.query(query, [idx], (err, results) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    if (results.length === 0) {
      return res.status(404).json({ message: 'Data not found' });
    }
    const item = {
        idx: results[0].IDX,
        title: results[0].TITLE,
        contents: results[0].CONTENTS,
        createdBy: results[0].createdBy,
        createdAt: results[0].createdAt,
    };
    res.json({data : item});
  });
});

// POST /board - 데이터 생성
app.post('/board', (req, res) => {
  const { title, contents, createdBy } = req.body;
  const query = 'INSERT INTO TB_BOARD (TITLE, CONTENTS, CREATED_BY, CREATED_AT) VALUES (?, ?, ?, NOW())';

  db.query(query, [title, contents, createdBy], (err, result) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.json({ message: 'Data inserted (데이터 생성)', id: result.insertId });
  });
});

// PATCH /board - 데이터 수정
app.patch('/board', (req, res) => {
  const { idx, title, contents } = req.body;
  const query = 'UPDATE TB_BOARD SET TITLE = ?, CONTENTS = ? WHERE IDX = ?';

  db.query(query, [title, contents, idx], (err, result) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.json({ message: 'Data updated (데이터 수정)' });
  });
});

// DELETE /board/:idx - 데이터 삭제
app.delete('/board/:idx', (req, res) => {
  const idx = req.params.idx;

  const query = 'DELETE FROM TB_BOARD WHERE IDX = ?';
  db.query(query, [idx], (err, result) => {
    if (err) {
      return res.status(500).json({ error: err.message });
    }
    res.json({ message: 'Data deleted (데이터 삭제)' });
  });
});

// 서버 실행
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});