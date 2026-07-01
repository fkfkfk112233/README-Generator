package dao.impl;

import dao.BaseDao;
import dao.ProjectDao;
import model.ProjectInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDao implements ProjectDao {

    @Override
    public int insert(ProjectInfo project) {

        String sql =
                "INSERT INTO project(" +
                        "project_name," +
                        "description," +
                        "author," +
                        "version," +
                        "github_url," +
                        "installation," +
                        "usage_text," +
                        "features," +
                        "create_time)" +
                        " VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps =
                    conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getAuthor());
            ps.setString(4, project.getVersion());
            ps.setString(5, project.getGithubUrl());
            ps.setString(6, project.getInstallation());
            ps.setString(7, project.getUsage());
            ps.setString(8, project.getFeatures());

            if (project.getCreateTime() == null) {
                ps.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(9, Timestamp.valueOf(project.getCreateTime()));
            }

            int row = ps.executeUpdate();

            if (row > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("新增專案失敗", e);
        }

        return -1;
    }

    @Override
    public boolean update(ProjectInfo project) {

        String sql =
                "UPDATE project SET " +
                        "project_name=?," +
                        "description=?," +
                        "author=?," +
                        "version=?," +
                        "github_url=?," +
                        "installation=?," +
                        "usage_text=?," +
                        "features=? " +
                        "WHERE id=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getAuthor());
            ps.setString(4, project.getVersion());
            ps.setString(5, project.getGithubUrl());
            ps.setString(6, project.getInstallation());
            ps.setString(7, project.getUsage());
            ps.setString(8, project.getFeatures());
            ps.setInt(9, project.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("更新專案失敗", e);
        }

    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM project WHERE id=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("刪除專案失敗", e);
        }

    }

    @Override
    public ProjectInfo findById(int id) {

        String sql = "SELECT * FROM project WHERE id=?";

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                ProjectInfo p = new ProjectInfo();

                p.setId(rs.getInt("id"));
                p.setProjectName(rs.getString("project_name"));
                p.setDescription(rs.getString("description"));
                p.setAuthor(rs.getString("author"));
                p.setVersion(rs.getString("version"));
                p.setGithubUrl(rs.getString("github_url"));
                p.setInstallation(rs.getString("installation"));
                p.setUsage(rs.getString("usage_text"));
                p.setFeatures(rs.getString("features"));

                Timestamp ts = rs.getTimestamp("create_time");
                if (ts != null) {
                    p.setCreateTime(ts.toLocalDateTime());
                }

                return p;

            }

        } catch (SQLException e) {
            throw new RuntimeException("查詢專案失敗", e);
        }

        return null;
    }

    @Override
    public List<ProjectInfo> findAll() {

        String sql = "SELECT * FROM project ORDER BY id DESC";

        List<ProjectInfo> list = new ArrayList<>();

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ProjectInfo p = new ProjectInfo();

                p.setId(rs.getInt("id"));
                p.setProjectName(rs.getString("project_name"));
                p.setDescription(rs.getString("description"));
                p.setAuthor(rs.getString("author"));
                p.setVersion(rs.getString("version"));
                p.setGithubUrl(rs.getString("github_url"));
                p.setInstallation(rs.getString("installation"));
                p.setUsage(rs.getString("usage_text"));
                p.setFeatures(rs.getString("features"));

                Timestamp ts = rs.getTimestamp("create_time");
                if (ts != null) {
                    p.setCreateTime(ts.toLocalDateTime());
                }

                list.add(p);

            }

        } catch (SQLException e) {
            throw new RuntimeException("查詢所有專案失敗", e);
        }

        return list;
    }

}