/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import com.github.fge.filesystem.driver.ExtendedFileSystemDriverBase.ExtendedFileAttributesFactory;
import se.vidstige.jadb.RemoteFile;


/**
 * AdbFileAttributesFactory.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
public final class AdbFileAttributesFactory extends ExtendedFileAttributesFactory {

    public AdbFileAttributesFactory() {
        setMetadataClass(RemoteFile.class);
        addImplementation("basic", AdbBasicFileAttributesProvider.class);
    }
}
