package com.ict.internCollab.repository;

import com.ict.internCollab.entity.JobPostActivity;
import com.ict.internCollab.entity.JobSeekerProfile;
import com.ict.internCollab.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    public List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);

}
