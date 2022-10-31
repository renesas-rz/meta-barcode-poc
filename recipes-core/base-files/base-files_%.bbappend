do_install_prepend () {
	if [ -f "${WORKDIR}/profile" ]; then
		echo "alias ll='ls -al'" >> ${WORKDIR}/profile
	fi
}
