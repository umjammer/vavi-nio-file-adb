/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import com.github.fge.filesystem.driver.FileSystemDriver;
import com.github.fge.filesystem.provider.FileSystemRepositoryBase;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;


/**
 * AdbFileSystemRepository.
 * <p>
 * env
 * <ul>
 * <li> volumeName ... string
 * </ul>
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
public final class AdbFileSystemRepository extends FileSystemRepositoryBase {

    /** */
    public AdbFileSystemRepository() {
        super("adb", new AdbFileSystemFactoryProvider());
    }

    /**
     */
    @Override
    public FileSystemDriver createDriver(URI uri, Map<String, ?> env) throws IOException {
        JadbConnection jadb = new JadbConnection();
        JadbDevice device = jadb.getAnyDevice();

        AdbFileStore fileStore = new AdbFileStore(device, factoryProvider.getAttributesFactory());
        return new AdbFileSystemDriver(fileStore, factoryProvider, device, env);
    }
}
