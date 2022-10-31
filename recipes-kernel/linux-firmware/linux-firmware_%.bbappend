FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
	${KERNELORG_MIRROR}/linux/kernel/firmware/${BPN}-20220509.tar.xz;name=linux-firmware-20220509;destsuffix=linux-firmware-20220509 \
	git://github.com/murata-wireless/cyw-fmac-nvram.git;protocol=https;branch=master;name=cyw-fmac-nvram;destsuffix=cyw-fmac-nvram \
"

SRC_URI[linux-firmware-20220509.sha256sum] = "376e0b3d7b4f8aaa2abf7f5ab74803dcf14b06b94e3d841b1467cd9a2848255e"
SRCREV_cyw-fmac-nvram = "d0ddc35f8ade6ba5629c3a6d0a9c810078a9ebbc"

do_install_append() {
	install -m 0644 ${WORKDIR}/linux-firmware-20220509/cypress/cyfmac4373-sdio.bin ${D}${nonarch_base_libdir}/firmware/cypress/cyfmac4373-sdio.bin
	install -m 0644 ${WORKDIR}/cyw-fmac-nvram/cyfmac4373-sdio.2AE.txt ${D}${nonarch_base_libdir}/firmware/cypress/cyfmac4373-sdio.txt
}

FILES_${PN}-bcm4373_append = " \
	${nonarch_base_libdir}/firmware/cypress/cyfmac4373-sdio.bin \
	${nonarch_base_libdir}/firmware/cypress/cyfmac4373-sdio.txt \
"

# There is a bug with the base linux-firmware recipe, the linux-firmware-bcm4373
# package contains a symbolic link, but the file the symbolic link points to
# is left behind by mistake, and it gets caught by the linux-firmware package,
# as a result package linux-firmware-bcm4373 depends on package linux-firmware,
# defeating the purpose of breaking linux-firmware in smaller chuncks.
# The below statement solves the aforementioned issue by adding the file pointed
# by the symbolic link to the linux-firmware-bcm4373 package.
FILES_${PN}-bcm4373_append = " \
	${nonarch_base_libdir}/firmware/cypress/cyfmac4373-sdio.clm_blob \
"
