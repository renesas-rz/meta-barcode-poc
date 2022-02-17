SUMMARY = "The image for the barcode scanner"

IMAGE_INSTALL = " \
	packagegroup-core-boot \
	${CORE_IMAGE_EXTRA_INSTALL} \
	media-ctl \
	ldd \
	strace \
	devmem2 \
	kernel-image \
	kernel-devicetree \
	i2c-tools \
	openssh \
	libiio \
	pv \
	tmux \
	e2fsprogs-badblocks \
	e2fsprogs-dumpe2fs \
	e2fsprogs-e2fsck \
	e2fsprogs-e2scrub \
	e2fsprogs-mke2fs \
	e2fsprogs-resize2fs \
	e2fsprogs-tune2fs \
	dosfstools \
	evtest \
	usbutils \
	barcode-poc \
"

IMAGE_LINGUAS = " "

IMAGE_FEATURES += "package-management"

LICENSE = "MIT"

inherit core-image

# 1 GB
IMAGE_ROOTFS_SIZE ?= "1048576"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

IMAGE_FSTYPES_append += "wic.gz"
WKS_FILE = "sdcard.wks"
