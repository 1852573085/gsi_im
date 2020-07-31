cmake_minimum_required(VERSION 3.4.1)
set(SRC_DIR ${CMAKE_SOURCE_DIR}/lamesource)

#message(WARNING ${CMAKE_SOURCE_DIR})

#导入头文件
include_directories(${SRC_DIR})

#添加编译目录
aux_source_directory(${SRC_DIR} SRC_LIST)
add_library(
        lamemp3
        SHARED
        native-lib.cpp
        ${SRC_LIST})

find_library(
        log-lib
        log)

target_link_libraries(
        lamemp3
        ${log-lib})