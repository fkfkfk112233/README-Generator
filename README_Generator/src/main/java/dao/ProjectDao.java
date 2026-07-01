package dao;

import java.util.List;

import model.ProjectInfo;

public interface ProjectDao {

    /**
     * 新增專案
     */
    int insert(ProjectInfo project);

    /**
     * 修改專案
     */
    boolean update(ProjectInfo project);

    /**
     * 刪除專案
     */
    boolean delete(int id);

    /**
     * 查詢單一專案
     */
    ProjectInfo findById(int id);

    /**
     * 查詢所有專案
     */
    List<ProjectInfo> findAll();

}