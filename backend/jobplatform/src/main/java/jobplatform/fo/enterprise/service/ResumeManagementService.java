package jobplatform.fo.enterprise.service;

import org.springframework.stereotype.Service;

import jobplatform.fo.enterprise.domain.mapper.ResumeMapper;

@Service
public class ResumeManagementService {

	private final ResumeMapper resumeMapper;

	public ResumeManagementService(ResumeMapper resumeMapper) {
		this.resumeMapper = resumeMapper;
	}
	
}