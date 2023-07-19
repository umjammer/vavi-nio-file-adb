/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.FileStore;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.fge.filesystem.driver.DoubleCachedFileSystemDriver;
import com.github.fge.filesystem.exceptions.IsDirectoryException;
import com.github.fge.filesystem.exceptions.UnsupportedOptionException;
import com.github.fge.filesystem.provider.FileSystemFactoryProvider;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import se.vidstige.jadb.RemoteFile;


/**
 * AdbFileSystemDriver.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
public final class AdbFileSystemDriver extends DoubleCachedFileSystemDriver<RemoteFile> {

    private JadbDevice fileSystem;

    public AdbFileSystemDriver(FileStore fileStore,
                               FileSystemFactoryProvider provider,
                               JadbDevice fileSystem,
                               Map<String, ?> env) throws IOException {
        super(fileStore, provider);
        this.fileSystem = fileSystem;
        setEnv(env);
    }

    @Override
    protected String getFilenameString(RemoteFile entry) {
        return entry.getPath();
    }

    @Override
    protected boolean isFolder(RemoteFile entry) {
        return entry.isDirectory();
    }

    @Override
    protected RemoteFile getRootEntry(Path path) throws IOException {
        return new RemoteFile(path.toString()) {
            @Override public int getSize() {
                return 0;
            }
            @Override public int getLastModified() {
                return 0;
            }
            @Override public boolean isDirectory() {
                return true;
            }
            @Override public Set<PosixFilePermission> permissions() {
                return Collections.emptySet();
            }
        };
    }

    @Override
    protected InputStream downloadEntryImpl(RemoteFile remoteFile, Path path, Set<? extends OpenOption> set) throws IOException {
        return fileSystem.pull(remoteFile);
    }

    @Override
    protected OutputStream uploadEntry(RemoteFile parentEntry, Path path, Set<? extends OpenOption> options) throws IOException {
        return fileSystem.push(Instant.now().getEpochSecond(), 0644, new RemoteFile(path.toString()));
    }

    @Override
    protected List<RemoteFile> getDirectoryEntries(RemoteFile dirEntry, Path dir) throws IOException {
        try {
            return fileSystem.list(dir.toString()).stream().filter(p -> !p.getPath().equals(".") && !p.getPath().equals("..")).collect(Collectors.toList());
        } catch (JadbException e) {
            throw new IOException(e);
        }
    }

    @Override
    protected RemoteFile createDirectoryEntry(RemoteFile parentEntry, Path dir) throws IOException {
        throw new UnsupportedOptionException("createDirectory");
    }

    @Override
    protected boolean hasChildren(RemoteFile dirEntry, Path dir) throws IOException {
        return getDirectoryEntries(dirEntry, dir).size() > 0;
    }

    @Override
    protected void removeEntry(RemoteFile entry, Path path) throws IOException {
        throw new UnsupportedOptionException("remove");
    }

    @Override
    protected RemoteFile copyEntry(RemoteFile sourceEntry, RemoteFile targetParentEntry, Path source, Path target, Set<CopyOption> options) throws IOException {
        throw new UnsupportedOptionException("copy");
    }

    @Override
    protected RemoteFile moveEntry(RemoteFile sourceEntry, RemoteFile targetParentEntry, Path source, Path target, boolean targetIsParent) throws IOException {
        throw new UnsupportedOptionException("move");
    }

    @Override
    protected RemoteFile moveFolderEntry(RemoteFile sourceEntry, RemoteFile targetParentEntry, Path source, Path target, boolean targetIsParent) throws IOException {
        // TODO java spec. allows empty folder
        throw new IsDirectoryException("source can not be a folder: " + source);
    }

    @Override
    protected RemoteFile renameEntry(RemoteFile sourceEntry, RemoteFile targetParentEntry, Path source, Path target) throws IOException {
        throw new UnsupportedOptionException("rename");
    }
}
