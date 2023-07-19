/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import java.nio.file.LinkOption;

import com.github.fge.filesystem.options.FileSystemOptionsFactory;


/**
 * AdbFileSystemOptionsFactory.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
public class AdbFileSystemOptionsFactory extends FileSystemOptionsFactory {

    public AdbFileSystemOptionsFactory() {
        addLinkOption(LinkOption.NOFOLLOW_LINKS);
    }
}

/* */
