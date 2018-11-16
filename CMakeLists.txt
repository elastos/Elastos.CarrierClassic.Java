cmake_minimum_required(VERSION 3.5.1)
set(CMAKE_VERBOSE_MAKEFILE on)

set(CMAKE_SYSTEM_NAME Linux)

find_package(JNI)

set(carrier_include_DIR ${CMAKE_SOURCE_DIR}/c_jni_src/include)
set(carrier_library_DIR ${CMAKE_SOURCE_DIR}/src/main/resources/lib)
set(LIBRARY_OUTPUT_PATH ${CMAKE_SOURCE_DIR}/src/main/resources/lib)

include_directories(${carrier_include_DIR} ${JNI_INCLUDE_DIRS})
link_directories(${carrier_library_DIR})

set(SOURCES
    ${CMAKE_SOURCE_DIR}/c_jni_src/init.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/utils.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/utilsExt.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/carrier.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/carrierHandler.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/carrierUtils.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/session.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/sessionManager.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/sessionUtils.c
    ${CMAKE_SOURCE_DIR}/c_jni_src/stream.c)

add_library(carrierjni SHARED ${SOURCES})

target_link_libraries(carrierjni
                      crystal
                      elacarrier
                      elasession)
