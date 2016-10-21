package task.service.impl;

import by.epam.lab.task.entity.Tag;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.TagRepository;
import by.epam.lab.task.service.impl.TagServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import java.util.ArrayList;
<<<<<<< HEAD:news-common/src/test/java/by/epam/lab/task/service/impl/TestTagService.java
import java.util.List;
=======
>>>>>>> develop/netcracker:news-common-service/src/test/java/task/service/impl/TestTagService.java

/**
 * @author Ivan Dzinhala
 */
public class TestTagService {
    @Mock
    private TagRepository tagRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void createTagTest() throws DAOException, ServiceException {
        Tag tag = new Tag();
        ArrayList<Tag> tagList = new ArrayList<>();
        Mockito.when(tagRepository.readAll()).thenReturn(tagList);
        tagService.create(tag);
        Mockito.verify(tagRepository).create(tag);
    }

    @Test
    public void deleteTagTest() throws DAOException, ServiceException {
        Tag tag = new Tag();
        tagService.delete(tag);
        Mockito.verify(tagRepository).delete(tag.getId());
    }

    @Test
    public void readByIdTest() throws DAOException, ServiceException {
        Long tagId=1l;
        tagService.readById(tagId);
        Mockito.verify(tagRepository).read(tagId);
    }

    @Test
    public void readTagsIdByNewsIdTest() throws DAOException, ServiceException {

        Long newsId = 1L;
        Long tagId = 1L;
        Tag tag = new Tag();
        tag.setId(tagId);
        ArrayList<Long> listOfTagId = new ArrayList<>();
        listOfTagId.add(tagId);

        Mockito.when(tagRepository.readTagsIdByNewsId(newsId)).thenReturn(listOfTagId);
        Mockito.when(tagRepository.read(tag.getId())).thenReturn(tag);

        tagService.readTagsByNewsId(newsId);

        Mockito.verify(tagRepository).readTagsIdByNewsId(newsId);
        Mockito.verify(tagRepository).read(tag.getId());
    }

    @Test
    public void updateTest() throws DAOException,ServiceException {
        Tag tag = new Tag();
        tagService.update(tag);
        Mockito.verify(tagRepository).update(tag.getId(),tag);
    }
    @Test
    public void readAllTest()throws DAOException,ServiceException{
        Tag tagExpected = new Tag();
        ArrayList<Tag> tagsExpected=new ArrayList<>();
        tagsExpected.add(tagExpected);
        Mockito.when(tagRepository.readAll()).thenReturn(tagsExpected);
        List<Tag> tags =tagService.readAll();
        assertTrue(tags.equals(tagsExpected));
    }
}
