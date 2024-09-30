SUMMARY = "AMI hello-world"
DESCRIPTION = "Test app from AMI"

# FILESEXTRAPATHS_prepend := "${THISDIR}:"

LICENSE = "CLOSED"

SRC_URI = "file://hello.c \
           file://hello.h \
           file://hello-ami.service \
            "

DEPENDS += " dbus"
S = "${WORKDIR}"

APP_NAME = "hello-ami"
bindir = "/usr/bin"

TARGET_CC_ARCH += "${LDFLAGS}"
# PARALLEL_MAKE = ""
#INSANE_SKIP_${PN} += "ldflags"
#RDEPENDS_${PN} += "bash"
inherit systemd
SERVICE_NAME = "hello-ami.service"
srv_bindir = "/etc/systemd/system"

do_compile() {
    ${CXX} hello.c -o ${APP_NAME}
}

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${APP_NAME} ${D}${bindir}

	install -d ${D}${srv_bindir}
	install -m 0644 ${SERVICE_NAME} ${D}${srv_bindir}
}

