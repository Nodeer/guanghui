package com.guanghui.car.communications;

import java.io.IOException;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.ServiceConnectionSE;

/**
 * 重写Soap的HttpTransportSE类
 * 系统的HttpTransportSE没有超时功能，此类通过设置ServiceConnectionSE中的构造函数来设置超时
 * 使用的时候代替系统的HttpTransportSE类就可以了
 * @author 张云
 *
 */
class MyAndroidHttpTransport extends HttpTransportSE {
	private int timeout = 20000; // 默认超时时间为20s

	public MyAndroidHttpTransport(String url) {

		super(url);
	}

	public MyAndroidHttpTransport(String url, int timeout) {
		super(url);
		this.timeout = timeout;
	}
	
//	protected ServiceConnection getServiceConnection() throws IOException {
//		ServiceConnectionSE serviceConnection = new ServiceConnectionSE(
//				this.url, timeout);
//		
//		
//		return serviceConnection;
//	}
}