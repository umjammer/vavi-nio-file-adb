/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import com.github.fge.filesystem.provider.FileSystemProviderBase;


/**
 * AdbFileSystemProvider.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
public final class AdbFileSystemProvider extends FileSystemProviderBase {

    public AdbFileSystemProvider() {
        super(new AdbFileSystemRepository());
    }
}
