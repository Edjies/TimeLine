package com.edjies.timeline.module.project.dao;

import android.database.Cursor;

import com.edjies.timeline.MyApplication;
import com.edjies.timeline.module.project.bean.DaoSession;
import com.edjies.timeline.module.project.bean.Project;
import com.edjies.timeline.module.project.bean.ProjectDao;
import com.edjies.timeline.module.project.bean.ProjectItem;
import com.edjies.timeline.module.project.bean.ProjectItemDao;
import com.edjies.timeline.module.project.bean.ProjectStatus;
import com.edjies.timeline.utils.UDate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 项目模块数据访问接口
 * Created by hubble on 2016/11/21 0021.
 */

public class ProjectDaoInterface {
    private static ProjectDaoInterface mDao = new ProjectDaoInterface();
    private ProjectDaoInterface() {

    }
    public static ProjectDaoInterface getInstance() {
        return mDao;
    }


    // 获取我的项目列表
    public ArrayList<Project> getMyProjects() {
        String sql = "select p.*," +
                "(select count(1) from PROJECT_ITEM pi where p._ID == pi.PROJECT_ID and pi.STATUS == "+ ProjectStatus.STATUS_CPT + ") as finished, " +
                "(select count(1) from PROJECT_ITEM pi where p._ID == pi.PROJECT_ID) as total " +
                "from PROJECT p;";
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectDao projectDao = session.getProjectDao();
        Cursor c = projectDao.getDatabase().rawQuery(sql, null);
        ArrayList<Project> projects = new ArrayList<>();
        while (c.moveToNext()) {
            Project project = new Project(c.getLong(c.getColumnIndex(ProjectDao.Properties.Id.columnName)),
                    c.getString(c.getColumnIndex(ProjectDao.Properties.Title.columnName)),
                    c.getString(c.getColumnIndex(ProjectDao.Properties.CreateTime.columnName)));
            project.setFinished(c.getInt(c.getColumnIndex("finished")));
            project.setTotal(c.getInt(c.getColumnIndex("total")));
            projects.add(project);
        }
        return projects;
    }

    // 获取我的项目详情列表
    public ArrayList<ProjectItem> getMyProjectDetails(Long projectId) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectItemDao projectDao = session.getProjectItemDao();
        ArrayList<ProjectItem> projectDetails = (ArrayList<ProjectItem>)projectDao.queryBuilder().where(ProjectItemDao.Properties.ProjectId.eq(projectId)).list();
        return projectDetails;
    }

    // 添加项目
    public void addProject(Project project) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectDao projectDao = session.getProjectDao();
        projectDao.insert(project);
    }

    // 添加项目任务
    public void addProjectItem(ProjectItem item) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectItemDao projectItemDao = session.getProjectItemDao();
        projectItemDao.insert(item);
    }

    // 更新项目任务
    public void updateProjectItem(ArrayList<ProjectItem> datas) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectItemDao projectItemDao = session.getProjectItemDao();
        projectItemDao.updateInTx(datas);
    }

    public void updateProjectItem(ProjectItem data) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectItemDao projectItemDao = session.getProjectItemDao();
        projectItemDao.update(data);
    }

    public void deleteProjectItem(ProjectItem item) {
        DaoSession session = MyApplication.getInstance().getDaoSession();
        ProjectItemDao projectItemDao = session.getProjectItemDao();
        projectItemDao.delete(item);
    }


}
