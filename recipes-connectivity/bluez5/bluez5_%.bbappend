do_install_append () {
	install -m 0644 ${B}/gdbus/.libs/libgdbus-internal.a ${D}${libdir}
}
