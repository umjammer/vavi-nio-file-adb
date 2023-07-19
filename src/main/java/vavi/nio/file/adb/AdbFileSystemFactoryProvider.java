/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import com.github.fge.filesystem.provider.FileSystemFactoryProvider;


/**
 * AdbFileSystemFactoryProvider.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
public final class AdbFileSystemFactoryProvider extends FileSystemFactoryProvider {

    public AdbFileSystemFactoryProvider() {
        setAttributesFactory(new AdbFileAttributesFactory());
        setOptionsFactory(new AdbFileSystemOptionsFactory());
    }
}
