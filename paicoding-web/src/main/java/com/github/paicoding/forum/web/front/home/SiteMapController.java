package com.github.paicoding.forum.web.front.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.paicoding.forum.service.sitemap.model.SiteMapVo;
import com.github.paicoding.forum.service.sitemap.service.SitemapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * 生成 sitemap.xml
 *
 * @author YiHui
 * @date 2023/2/13
 */
@RestController
public class SiteMapController {
    private XmlMapper xmlMapper = new XmlMapper();
    @Resource
    private SitemapService sitemapService;

    @RequestMapping(path = "/sitemap",
            produces = "application/xml;charset=utf-8")
    public SiteMapVo sitemap() {
        return sitemapService.getSiteMap();
    }

    @RequestMapping(path = "/sitemap.xml",
            produces = "text/xml")
    public byte[] sitemapXml() throws JsonProcessingException {
        SiteMapVo vo = sitemapService.getSiteMap();
        String ans = xmlMapper.writeValueAsString(vo);
        return ans.getBytes(Charset.defaultCharset());
    }

    @GetMapping(path = "/sitemap/refresh")
    public Boolean refresh() {
        sitemapService.refreshSitemap();
        return true;
    }
}
