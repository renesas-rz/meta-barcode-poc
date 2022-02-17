SUMMARY = "An open source library for reading barcodes"
DESCRIPTION = "ZBar is an open source software suite for reading barcodes \
from various sources, such as video streams, image files and raw intensity \
sensors. It supports many popular symbologies (types of bar codes) including \
EAN-13/UPC-A, UPC-E, EAN-8, Code 128, Code 39, Interleaved 2 of 5 and QR Code."

LICENSE = "LGPL-2.1"

DEPENDS = "pkgconfig intltool-native libpng jpeg"

LIC_FILES_CHKSUM = " \
	file://COPYING;md5=4015840237ca7f0175cd626f78714ca8 \
	file://LICENSE.md;md5=5e9ee833a2118adc7d8b5ea38e5b1cef \
"

# 0.23.92 tag
SRCREV = "aac86d5f08d64ab4c3da78188eb622fa3cb07182"
SRC_URI = " \
	git://github.com/mchehab/zbar \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"

PACKAGECONFIG[x11] = "--with-x,-without-x,libxcb libx11 libsm libxau libxext libxv libice libxdmcp"

EXTRA_OECONF = "--without-imagemagick --without-qt --without-python --disable-video --without-gtk --disable-nls"

CPPFLAGS += "-Wno-error"

do_install_append () {
	rm -rf ${D}/usr/bin
}
