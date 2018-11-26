cmake_minimum_required(VERSION 3.5.1)
set(CMAKE_VERBOSE_MAKEFILE on)

set(MINGW_TOOLCHAIN_REFIX "x86_64-w64-mingw32-")
set(CMAKE_C_COMPILER "${MINGW_TOOLCHAIN_REFIX}gcc")
set(CMAKE_CXX_COMPILER "${MINGW_TOOLCHAIN_REFIX}g++")
set(CMAKE_CPP_COMPILER "${MINGW_TOOLCHAIN_REFIX}c++ -E")
set(CMAKE_AR "${MINGW_TOOLCHAIN_REFIX}gcc-ar" CACHE PATH "")
set(CMAKE_RANLIB "${MINGW_TOOLCHAIN_REFIX}gcc-ranlib" CACHE PATH "")

set(carrier_include_DIR ${CMAKE_SOURCE_DIR}/include)
set(carrier_library_DIR ${CMAKE_SOURCE_DIR}/../../src/main/resources/lib/windows)
set(LIBRARY_OUTPUT_PATH ${CMAKE_SOURCE_DIR}/../../src/main/resources/lib/windows)

find_package(JNI)

include_directories(${carrier_include_DIR} ${JNI_INCLUDE_DIRS})
link_directories(${carrier_library_DIR})

set(SOURCES
	${CMAKE_SOURCE_DIR}/init.c
	${CMAKE_SOURCE_DIR}/utils.c
	${CMAKE_SOURCE_DIR}/utilsExt.c
	${CMAKE_SOURCE_DIR}/carrier.c
	${CMAKE_SOURCE_DIR}/carrierHandler.c
	${CMAKE_SOURCE_DIR}/carrierUtils.c
	${CMAKE_SOURCE_DIR}/session.c
	${CMAKE_SOURCE_DIR}/sessionManager.c
	${CMAKE_SOURCE_DIR}/sessionUtils.c
	${CMAKE_SOURCE_DIR}/stream.c)

add_library(carrierjni SHARED ${SOURCES})

target_link_libraries(carrierjni crystal elacarrier elasession)