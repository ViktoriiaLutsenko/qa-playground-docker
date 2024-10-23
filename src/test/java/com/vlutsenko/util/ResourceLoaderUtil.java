package com.vlutsenko.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ResourceLoaderUtil {
    private static final Logger log = LoggerFactory.getLogger(ResourceLoaderUtil.class);

    public static InputStream getResource(String path) throws Exception {
        log.debug("Reading resource from location: {}", path);
        InputStream stream = ResourceLoaderUtil.class.getClassLoader().getResourceAsStream(path);
        if (Objects.nonNull(stream)) {
            return stream;
        }
        return Files.newInputStream(Path.of(path));
    }
}
