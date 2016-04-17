package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.TagRepository;
import by.epam.lab.task1.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 * @see TagService
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    private final static Logger logger= Logger.getLogger(TagServiceImpl.class);

    @Autowired
    private TagRepository tagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(Tag tag) throws ServiceException {
        logger.debug("Creating tag in TagService");
        Long tagId=null;
        try{
            tagId=tagRepository.create(tag);
        } catch (DAOException e) {
            logger.error("DAOException while creating tag in TagService");
            throw new ServiceException("ServiceException while creating tag",e);
        }
        return tagId;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag readById(Long id) throws ServiceException {
        logger.debug("Reading tag by id in TagService");
        Tag tag = null;
        try{
            tag=tagRepository.read(id);
        } catch (DAOException e) {
            logger.error("DAOException while reading tag by id in TagService");
            throw new ServiceException("ServiceException while reading tag",e);
        }
        return tag;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArrayList<Tag> readTagsByNewsId(Long newsId) throws ServiceException {
        logger.debug("Reading tags by news id in TagService");
        ArrayList<Tag> tags=null;
        ArrayList<Long> tagIds=null;
        try{
            tagIds=tagRepository.readTagsIdByNewsId(newsId);
            tags=new ArrayList<>();
            for(Long tagId:tagIds){
                tags.add(tagRepository.read(tagId));
            }
        } catch (DAOException e) {
            logger.error("DAOException while reading tags by news id in TagService");
            throw new ServiceException("ServiceException while reading tags by news id",e);
        }
        return null;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Tag tag) throws ServiceException {
        logger.debug("Updating tag in TagService");
        try{
            tagRepository.update(tag.getId(),tag);
        } catch (DAOException e) {
            logger.error("DAOException while updating tag in TagService");
            throw new ServiceException("ServiceException while updating tag",e);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Tag tag) throws ServiceException {
        logger.debug("Deleting tag in TagService");
        try{
            tagRepository.delete(tag.getId());
        } catch (DAOException e) {
            logger.error("DAOException while deleting tag in TagService");
            throw new ServiceException("ServiceException while deleting tag",e);
        }
    }
}
