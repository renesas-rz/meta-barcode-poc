FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "(smarc-rzg2l|rzg2l-dev|rzg2lc-dev|smarc-rzg2lc|barcode-poc-g2l)"

SRC_URI_append += " \
	file://g2l-patches.scc \
	file://barcode-poc.cfg \
"

KERNEL_MODULE_AUTOLOAD += "g_mass_storage"
KERNEL_MODULE_PROBECONF += "g_mass_storage"
module_conf_g_mass_storage = "options g_mass_storage file=/dev/mmcblk0p2"
