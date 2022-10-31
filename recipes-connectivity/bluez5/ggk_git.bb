SUMMARY = "A standalone Linux Bluetooth LE GATT server using BlueZ over D-Bus"
DESCRIPTION = "Gobbledegook is a C/C++ standalone Linux Bluetooth LE GATT \
server using BlueZ over D-Bus with Bluetooth Management API support built in."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = " \
	file://LICENSE;md5=1b7589aa88a1dbbdd35dac2cc62eae4d \
"

SRCREV = "50b2ae2237ea6e8b1466d64e3add33acefe921a0"
PV = "dev+git${SRCREV}"

SRC_URI = " \
	git://github.com/nettlep/gobbledegook.git;protocol=https;branch=master \
	file://0001-Renesas-Barcode-PoC-changes.patch \
	file://barcodepoc.conf \
"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

PACKAGES += "${PN}-standalone"

DEPENDS = " \
	bluez5 \
	glib-2.0 \
"

do_install () {
	install -d ${D}${bindir}
	install -m 777 ${B}/src/standalone ${D}${bindir}

	install -d ${D}${includedir}
	install -m 644 ${S}/include/Gobbledegook.h ${D}${includedir}

	install -d ${D}${libdir}
	install -m 644 ${B}/src/libggk.a ${D}${libdir}

	install -d ${D}/etc/dbus-1/system.d/
	install -m 0644 ${WORKDIR}/barcodepoc.conf ${D}/etc/dbus-1/system.d/
}

FILES_${PN}-standalone = " \
	${bindir}/standalone \
"

FILES_${PN} = " \
	/etc/dbus-1/system.d/ \
"
