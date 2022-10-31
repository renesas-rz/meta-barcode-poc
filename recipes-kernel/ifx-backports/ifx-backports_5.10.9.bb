SUMMARY = "IFX Backports"
DESCRIPTION = "Build Broadcom FMAC WiFi modules from ifx-backports. Based on backporttool-linux_1.0.bb from meta-cywlan."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/v5.10.9-backports/COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit linux-kernel-base kernel-arch module-base

DEPENDS += "bison-native flex-native linux-renesas"

# release-v5.10.9-2021_1020
SRCREV = "03065fa919993b956dcca347811468c2e9515dd3"
SRC_URI = "git://github.com/Infineon/ifx-backports.git;protocol=https"

S = "${WORKDIR}/git/v${PV}-backports"
B = "${S}"

EXTRA_OEMAKE += "LEX=flex"

do_configure[noexec] = "1"

do_compile() {
	(
		export CC="gcc"
		oe_runmake KLIB="${STAGING_KERNEL_DIR}" KLIB_BUILD="${STAGING_KERNEL_BUILDDIR}" defconfig-brcmfmac
		oe_runmake KLIB="${STAGING_KERNEL_DIR}" KLIB_BUILD="${STAGING_KERNEL_BUILDDIR}" oldconfig
	)
	oe_runmake KLIB="${STAGING_KERNEL_DIR}" KLIB_BUILD="${STAGING_KERNEL_BUILDDIR}" modules
}

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/broadcom/brcm80211/brcmfmac
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/broadcom/brcm80211/brcmutil
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/compat
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/wireless

	install -m 644 \
		${S}/drivers/net/wireless/broadcom/brcm80211/brcmfmac/brcmfmac.ko \
		${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/broadcom/brcm80211/brcmfmac/brcmfmac.ko
	install -m 644 \
		${S}/drivers/net/wireless/broadcom/brcm80211/brcmutil/brcmutil.ko \
		${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/broadcom/brcm80211/brcmutil/brcmutil.ko
	install -m 644 \
		${S}/compat/compat.ko \
		${D}/lib/modules/${KERNEL_VERSION}/kernel/compat/compat.ko
	install -m 644 \
		${S}/net/wireless/cfg80211.ko \
		${D}/lib/modules/${KERNEL_VERSION}/kernel/net/wireless/cfg80211.ko
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} += " \
	/lib \
"

PACKAGES = "${PN}"
RPROVIDES_${PN} += " \
	kernel-module-brcmfmac \
	kernel-module-brcmutil \
	kernel-module-compat \
	kernel-module-cfg80211 \
"
