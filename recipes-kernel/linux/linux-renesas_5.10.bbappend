FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE_rzg2l = "(smarc-rzg2l|smarc-rzg2lc|smarc-rzg2ul|barcode-poc-g2l)"

SRC_URI_append += " \
	file://barcode-patches.scc \
	file://barcode-poc.cfg \
	file://wifi.cfg \
"

do_install_append() {
	# The backports for the WiFi driver and the configuration might clash on the modules
	# generated. Get rid of the network directory to avoid issues.
	rm -rf ${D}/lib/modules/*/kernel/net/
}
