package service.impl;

import java.util.List;

import dao.ProjectDao;
import dao.impl.ProjectDaoImpl;
import model.ProjectInfo;
import service.ProjectService;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectDao projectDao;

    public ProjectServiceImpl() {
        this.projectDao = new ProjectDaoImpl();
    }

    @Override
    public int insert(ProjectInfo project) {

        if (project == null) {
            return -1;
        }

        if (project.getProjectName() == null ||
                project.getProjectName().trim().isEmpty()) {

            throw new IllegalArgumentException("Project Name 不可為空");
        }

        return projectDao.insert(project);

    }

    @Override
    public boolean update(ProjectInfo project) {

        if (project == null) {
            return false;
        }

        return projectDao.update(project);

    }

    @Override
    public boolean delete(int id) {

        return projectDao.delete(id);

    }

    @Override
    public ProjectInfo findById(int id) {

        return projectDao.findById(id);

    }

    @Override
    public List<ProjectInfo> findAll() {

        return projectDao.findAll();

    }

}
