require barcode-poc.inc

PR = "r1"

BARCODE_POC_URI ??= "git://git@github.com/renesas-rz/barcode-poc.git;protocol=ssh"
SRCREV = "6ba13f1245b18cccfc4fecb9e27bb7c5db61b181"

SRC_URI += " \
	${BARCODE_POC_URI} \
"
