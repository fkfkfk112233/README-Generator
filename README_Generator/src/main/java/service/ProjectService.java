package service;

import java.util.List;

import model.ProjectInfo;

public interface ProjectService {

    /**
     * 新增專案
     *
     * @return 新增成功後的 id，失敗回傳 -1
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
     * 查詢全部專案
     */
    List<ProjectInfo> findAll();

}