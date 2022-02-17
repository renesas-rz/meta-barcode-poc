require barcode-poc.inc

SRCREV = "${AUTOREV}"
PV = "dev+git${SRCPV}"

BARCODE_POC_URI ??= "git://git@gitlab.renesas.solutions/spl2/barcode-scanner/barcode-poc.git;protocol=ssh;branch=master"

SRC_URI += " \
	${BARCODE_POC_URI} \
"
