package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.repository.AuthorRepository;
import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.DAOException;
import by.epam.lab.task1.exceptions.ServiceException;
import by.epam.lab.task1.service.AuthorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 */
@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    private final static Logger logger= Logger.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    private boolean isExisting(Author author)throws DAOException{
        ArrayList<Author> authors = authorRepository.readAll();
        return authors.contains(author);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(Author author) throws ServiceException {
        logger.debug("Creating author in AuthorService");
        Long authorId=null;
        try {
            if (!isExisting(author)) {
                logger.debug("Author dont exist in database, creating started");
                authorId = authorRepository.create(author);
            } else {
                logger.debug("Such author already exists");
                throw new ServiceException("Author name=" + author.getName() + " already exist");
            }
        } catch (DAOException e) {
            logger.error("DAOException while creating author in AuthorService");
            throw new ServiceException("ServiceException while creating an author", e);
        }
        return authorId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Author readByNewsId(Long newsId) throws ServiceException {
        logger.debug("Reading author by news id in AuthorService");
        Long authorId=null;
        Author author=null;
        try{
            authorId= authorRepository.readAuthorIdByNewsId(newsId);
            author = authorRepository.read(authorId);
        }catch (DAOException e){
            logger.error("DAOException while reading author by news in AuthorService");
            throw new ServiceException("ServiceException while reading an author by news id",e);
        }
        return author;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Author author) throws ServiceException {
        logger.debug("Deleting author in AuthorService");
        author.setExpired();
        try {
            authorRepository.update(author.getId(), author);
        }catch (DAOException e){
            logger.error("DAOException while deleting author in AuthorService");
            throw new ServiceException("ServiceException while deleting an author",e);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Author author) throws ServiceException {
        logger.debug("Updating author in AuthorService");
        try{
            authorRepository.update(author.getId(),author);
        }catch (DAOException e){
            logger.error("DAOException while updating author in AuthorService");
            throw new ServiceException("ServiceException while updating an author",e);
        }
    }
}
