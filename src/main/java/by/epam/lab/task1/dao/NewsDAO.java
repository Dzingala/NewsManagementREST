package by.epam.lab.task1.dao;

import by.epam.lab.task1.model.News;


public interface NewsDAO {
    public boolean createNews(News news);
    public News readNewsById(long newsId);
    public boolean updateNewsById(News news);
    public boolean deleteNewsById(long newsId);
}
