/*
 * Copyright (c) 2023 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.nio.file.adb;

import java.nio.file.attribute.PosixFilePermissions;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.libvirt.Connect;
import org.libvirt.Domain;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.RemoteFile;


/**
 * Test1
 *
 * 🍎🍎🍎 you need to {@code "adb start-server"} before running. 🍎🍎🍎
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2023/07/18 umjammer initial version <br>
 */
class Test1 {

    @Test
    @Disabled("server is needed? port 16514")
    @DisplayName("libvirt")
    void test1() throws Exception {
        Connect conn = new Connect("qcow2://localhost/.android/avd/Pixel_2_API_16.avd/sdcard.img.qcow2");
        System.out.println(conn.nodeInfo().cores);
        for (String name : conn.listDefinedDomains()) {
            System.out.println(name);
            if (name != null) {
                Domain domain = conn.domainLookupByName(name);
                System.out.println(domain.getMaxMemory());
                System.out.println(domain.getUUIDString());
                System.out.println(domain.getInfo().maxMem);
                System.out.println(domain.getInfo().state);
                System.out.println(conn.listDomains().length);
            }
        }
    }

    /**
     * you need to {@code "adb start-server"} before running.
     */
    @Test
    @DisplayName("jadb")
    void test2() throws Exception {
        JadbConnection jadb = new JadbConnection();
        List<JadbDevice> devices = jadb.getDevices();
devices.forEach(System.err::println);
        JadbDevice device = devices.get(0);
        try (var is = device.executeShell("ls -l")) {
System.err.println(new String(is.readAllBytes()));
        }
System.err.println("\n# ls -l /");
        for (RemoteFile f : device.list("/")) {
            System.err.printf("%s %8d %s %s%n", PosixFilePermissions.toString(f.permissions()), f.getSize(), LocalDateTime.ofInstant(Instant.ofEpochSecond(f.getLastModified()), ZoneId.systemDefault()), f.getPath());
        }
System.err.println("\n# ls -l /sdcard");
        for (RemoteFile f : device.list("/sdcard")) {
            System.err.printf("%s %8d %s %s%n", PosixFilePermissions.toString(f.permissions()), f.getSize(), LocalDateTime.ofInstant(Instant.ofEpochSecond(f.getLastModified()), ZoneId.systemDefault()), f.getPath());
        }
    }
}

/* */
