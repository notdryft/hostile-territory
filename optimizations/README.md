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
*              soft     nofile          200000
*              hard     nofile          200000
```

## Optimizing Kernel

/etc/sysctl.d/99-gatling.conf:
```
net.ipv4.ip_local_port_range = 1024 65535
net.ipv4.tcp_fin_timeout = 15

fs.nr_open = 300000 # File opened limit per process
net.ipv4.tcp_max_syn_backlog = 40000 # Max global new connections
net.ipv4.tcp_tw_reuse = 1 # Reuse socket in TIME_WAIT
net.core.netdev_max_backlog = 300000 # Max packets waiting on interface
net.core.somaxconn = 40000 # Max new connections per port

net.ipv4.tcp_window_scaling = 1 # Increase window receive size

net.ipv4.tcp_rmem = 4096 277750 134217728 # Buffers TCP connections (read)
net.ipv4.tcp_wmem = 4096 277750 134217728 # Buffers TCP connections (write)
net.ipv4.tcp_mem  = 134217728 134217728 134217728 # Max TCP memory

net.ipv4.tcp_keepalive_intvl = 30 # Interval between probes
net.ipv4.tcp_slow_start_after_idle = 0 # Disable idle
```
