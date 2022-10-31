SUMMARY = "Application for the Barcode PoC"
DESCRIPTION = "An application to demonstrate that RZ/G2L SoCs can be a good \
fit for barcode scanners."

LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = " \
	file://licenses/LICENSE.barcode-poc;md5=d29a7703d172b62a0bf91c6d3d42ee47 \
"

# Tag v2.0
SRCREV = "46408c2845e851e935713a8e5782a3305f14c921"
PR = "r1"

BARCODE_POC_URI ??= "git://git@github.com/renesas-rz/barcode-poc.git;protocol=ssh;branch=master"

SRC_URI = " \
	${BARCODE_POC_URI} \
	file://barcode-poc.init \
	file://barcode-poc.service \
"

DEPENDS += " \
	ggk \
	libwebsockets \
	media-ctl \
	opencv \
	xxd-native \
	zbar \
"

RDEPENDS_${PN} += " \
	bash \
	ggk \
"

inherit systemd autotools update-rc.d

S = "${WORKDIR}/git"
B = "${S}"

BARCODE_POC_CONFIGURATION_FILE ??= "barcode-poc.ini"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/barcode-poc.init ${D}${sysconfdir}/init.d/barcode-poc

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/barcode-poc.service ${D}${systemd_unitdir}/system/

	# Replace the default configuration file with the file we actually want
	# to use
	sed "s|\/[^\/]*.ini\$|\/${BARCODE_POC_CONFIGURATION_FILE}|g" -i \
		${D}${systemd_unitdir}/system/barcode-poc.service \
		${D}${sysconfdir}/init.d/barcode-poc

	install -d ${D}/data
}

SYSTEMD_SERVICE_${PN} = "barcode-poc.service"
SYSTEMD_AUTO_ENABLE = "enable"

INITSCRIPT_NAME = "barcode-poc"
INITSCRIPT_PARAMS = "start 99 2 3 4 5 . stop 99 0 1 6 ."

FILES_${PN} += " \
        ${datadir}/${PN} \
        ${nonarch_libdir}/${PN} \
	/data \
"

do_configure[noexec] = "1"
