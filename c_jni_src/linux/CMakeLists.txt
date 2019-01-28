cmake_minimum_required(VERSION 3.5.1)
set(CMAKE_VERBOSE_MAKEFILE on)

set(carrier_include_DIR ${CMAKE_SOURCE_DIR}/include)
set(carrier_library_DIR ${CMAKE_SOURCE_DIR}/../../src/main/resources/lib/linux)
set(LIBRARY_OUTPUT_PATH ${CMAKE_SOURCE_DIR}/../../src/main/resources/lib/linux)

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
	${CMAKE_SOURCE_DIR}/stream.c
	${CMAKE_SOURCE_DIR}/fileTransfer.c
    ${CMAKE_SOURCE_DIR}/fileTransferManager.c
    ${CMAKE_SOURCE_DIR}/fileTransferUtils.c)

add_library(carrierjni SHARED ${SOURCES})

target_link_libraries(carrierjni crystal elacarrier elasession elafiletransfer)