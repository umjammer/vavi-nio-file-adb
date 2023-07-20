
[![GitHub Packages](https://github.com/umjammer/vavi-nio-file-adb/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/umjammer/vavi-nio-file-adb/actions/workflows/maven-publish.yml)
[![Release](https://jitpack.io/v/umjammer/vavi-nio-file-adb.svg)](https://jitpack.io/#umjammer/vavi-nio-file-adb)
[![Java CI](https://github.com/umjammer/vavi-nio-file-adb/actions/workflows/maven.yml/badge.svg)](https://github.com/umjammer/vavi-nio-file-adb/actions/workflows/maven.yml)
[![CodeQL](https://github.com/umjammer/vavi-nio-file-adb/actions/workflows/codeql.yml/badge.svg)](https://github.com/umjammer/vavi-nio-file-adb/actions/workflows/codeql-analysis.yml)
![Java](https://img.shields.io/badge/Java-17-b07219)

# vavi-nio-file-adb

📱 mount the android world!

## Status

| fs         | list | upload | download | copy | move | rm | mkdir | cache | watch | library                                   |
|------------|------|--------|----------|------|------|----|-------|-------|-------|-------------------------------------------|
| adb        | ✅   | ✅    | ✅       |    |   |  |    | ✅   |       | [jadb](https://github.com/umjammer/jadb/) |
| adb (fuse) | ✅   | ✅    | ✅       |    |   |  |    | ✅   |       | [jadb](https://github.com/umjammer/jadb/) |

## Install

 * [maven](https://jitpack.io/#umjammer/vavi-nio-file-adb)


## TODO

 * integration test
 * make other functions using shell commands?
 * SyncTransport#readChunk is too slow, threading?

## References

 * adb
   * https://github.com/cstyan/adbDocumentation
   * https://github.com/vidstige/jadb 🎯
   * https://nosix.hatenablog.com/entry/2016/09/24/173052
   * https://github.com/zach-klippenstein/adbfs
   * https://github.com/spion/adbfs-rootless
 * file transferring
   * Device File Explorer on Android Studio
   * [Android File Transfer](https://www.android.com/filetransfer/)
 * mtp
   * https://github.com/pranav-prakash/jmtpfs
 * sdcard image is qcow2
   * [libvirt](https://libvirt.org/) ... too much? needs server? 
