## Optimizing Kernel

:warning: **Make sure to apply this change first.**

There is a bug in old kernels (RHEL based : RHEL/CentOS/Fedora) that locks file if `ulimit` is superior to `fs.nr_open`, which will make your life harder as you won't be able to sudo anything afterwards.
See https://unix.stackexchange.com/questions/432057/pam-limits-so-making-problems-for-sudo/444033#444033

/etc/sysctl.d/99-gatling.conf:
```
# Enhance connections limits

# Increase size of file handles and inode cache
fs.nr_open = 268435456
fs.file-max = 268435456

net.core.netdev_max_backlog = 300000 # Max packets waiting on interface
net.core.somaxconn = 40000 # Max new connections per port
net.ipv4.ip_local_port_range = 1024 65535 # Max TCP ports available
net.ipv4.tcp_max_syn_backlog = 40000 # Max global new connections

# Faster port recycling

net.ipv4.tcp_fin_timeout = 15 # Duration of TIME_WAIT_2
net.ipv4.tcp_tw_reuse = 1 # Reuse socket in TIME_WAIT

# Windows adjustments and buffers

net.ipv4.tcp_window_scaling = 1 # Increase window receive size

net.ipv4.tcp_rmem = 4096 277750 134217728 # Buffers TCP connections (read)
net.ipv4.tcp_wmem = 4096 277750 134217728 # Buffers TCP connections (write)
net.ipv4.tcp_mem  = 134217728 134217728 134217728 # Max TCP memory

# Minimize slow-start

net.ipv4.tcp_slow_start_after_idle = 0 # Disable idle

# Adjust TCP keep alive

net.ipv4.tcp_keepalive_intvl = 30 # Interval between probes
```

Then apply the changes without rebooting:

```
sudo sysctl -p /etc/sysctl.d/99-gatling.conf
```

## Enabling limits

### Debian

/etc/pam.d/common-session:

```
session    required     pam_limits.so
```

### RedHat

/etc/pam.d/sshd:

```
session    required     pam_limits.so
```

## Upgrading limits for all distributions

/etc/security/limits.conf:

```
*              soft     nofile          268435456
*              hard     nofile          268435456
```
