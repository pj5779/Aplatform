import React from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
//기본 게시물 컴포넌트
const Board = ({ idx, title, contents, createdBy }) => {
  const navigate = useNavigate();
  //수정버튼
  const moveToUpdate = () => {
    navigate('/update/' + idx);
  };
  //삭제버튼 함수
  const deleteBoard = async () => {
    if (window.confirm('게시글을 삭제하시겠습니까?')) {
      //idx는 게시물 번호
      await axios.delete(`/board/${idx}`).then((res) => {
        alert('삭제되었습니다.');
        navigate('/board');
      });
    }
  };

  const moveToList = () => {
    navigate('/board');
  };

  return (
    <div>
      <div>
        <h2>{title}</h2>
        <h5>{createdBy}</h5>
        <hr />
        <p>{contents}</p>
      </div>
      <div>
        <button onClick={moveToUpdate}>수정</button>
        <button onClick={deleteBoard}>삭제</button>
        <button onClick={moveToList}>목록</button>
      </div>
    </div>
  );
};

export default Board;