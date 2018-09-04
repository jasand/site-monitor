package no.sensor.service;

import no.sensor.db.jpa.SiteEntity;
import no.sensor.db.repo.SiteRepo;
import no.sensor.service.SiteService;
import no.sensor.service.impl.SiteServiceImpl;
import no.sensor.service.model.Site;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by jan.arne.sandnes on 16.10.15.
 */
@RunWith(MockitoJUnitRunner.class)
public class SiteServiceTest {

    @Test
    public void dummyTest() {
        assertTrue(true);
    }
//
//    @Mock
//    SiteRepo siteRepo;
//
//    @InjectMocks
//    private SiteService siteService = new SiteServiceImpl();
//
//    private Site site;
//    private SiteEntity siteEntity;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        site = new Site();
//        site.setSiteIdent("ident1");
//        site.setSiteName("SiteName1");
//        site.setSiteAddress("Addr1");
//        site.setContactPerson("cp1");
//        site.setContactPhone("12345678");
//        site.setContactEmail("c1@mail.no");
//
//        siteEntity = new SiteEntity();
//        siteEntity.setId(123);
//        siteEntity.setSiteIdent("ident1");
//        siteEntity.setSiteName("SiteName1");
//        siteEntity.setSiteAddress("Addr1");
//        siteEntity.setContactPerson("cp1");
//        siteEntity.setContactPhone("12345678");
//        siteEntity.setContactEmail("c1@mail.no");
//    }
//
//    @Test
//    public void verifyCreationOfSite() {
//        when(siteRepo.save(any(SiteEntity.class))).thenReturn(siteEntity);
//        Site s1 = siteService.createSite(site);
//        assertEquals("ident1", s1.getSiteIdent());
//        assertEquals("SiteName1", s1.getSiteName());
//        assertEquals("Addr1", s1.getSiteAddress());
//    }
//
//    @Test
//    public void verifyCanFetchSingleSiteById() {
//        when(siteRepo.findOne(123L)).thenReturn(siteEntity);
//        Site s2 = siteService.getSiteById(123L);
//        assertEquals("ident1", s2.getSiteIdent());
//        assertEquals("SiteName1", s2.getSiteName());
//        assertEquals("Addr1", s2.getSiteAddress());
//    }
}
