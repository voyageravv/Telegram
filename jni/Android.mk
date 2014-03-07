<<<<<<< HEAD
NDK_TOOLCHAIN_VERSION := 4.8
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := tmessages
LOCAL_CFLAGS = -w -std=gnu99 -O3
LOCAL_LDLIBS := -llog

LOCAL_SRC_FILES :=  \
./aes_core.c \
./aes_ige.c \
./aes_misc.c \
./jni.c \
./sqlite3.c \
./org_telegram_SQLite_SQLiteCursor.c \
./org_telegram_SQLite_SQLiteDatabase.c \
./org_telegram_SQLite_SQLitePreparedStatement.c \
./org_telegram_SQLite.c

 
include $(BUILD_SHARED_LIBRARY)
=======
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := gifdrawable
LOCAL_SRC_FILES := libgifdrawable.c dgif_lib.c gif_err.c gifalloc.c
LOCAL_LDLIBS    := -lm -llog -ljnigraphics -landroid

include $(BUILD_SHARED_LIBRARY)
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
