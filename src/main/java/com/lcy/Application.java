package com.lcy;

import com.lcy.util.common.ProjectPathUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

@SpringBootApplication(scanBasePackages = {"com.lcy.*"}, exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan(basePackages = "com.lcy.*")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    /**
     * 是否jar运行,用于判断配置文件要下载到哪边，根目录或者classes
     *
     * @return
     */
    private static boolean isRunJar() {
        URL url = Application.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.contains(".jar")) {// 可执行jar包运行的结果里包含".jar"
            return true;
        }
        return false;
    }

    /**
     * 获取验证国际化信息文件
     */
    private static void getMessageResource() {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //获取所有匹配的文件
            Resource[] resources = resolver.getResources("classpath*:messages/*.properties");
            String preFilePath = ProjectPathUtils.getClassesPath() + File.separatorChar + "messages" + File.separatorChar;
            if (Application.isRunJar()) {
                preFilePath = ProjectPathUtils.getRunTimePath() + File.separatorChar + "messages" + File.separatorChar;
            }
            for (Resource resource : resources) {

                // 过滤文件系统下的配置文件，只取出jar中的配置
                if (resource instanceof FileSystemResource) {
                    continue;
                }
                //获得文件流，因为在jar文件中，不能直接通过文件资源路径拿到文件，但是可以在jar包中拿到文件流
                InputStream stream = resource.getInputStream();
                String targetFilePath = preFilePath + resource.getFilename();
                File ttfFile = new File(targetFilePath);
                System.out.println(ttfFile.getAbsolutePath());
                FileUtils.copyInputStreamToFile(stream, ttfFile);
            }
        } catch (IOException e) {
        }
    }

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();

        //设置默认上传临时文件夹位置
        String location = ProjectPathUtils.getPath("config/filesystem/tmp");
        factory.setLocation(location);
        //  单个数据大小
        factory.setMaxFileSize("102400KB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

}
