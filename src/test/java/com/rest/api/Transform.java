//package com.rest.api;
//
//import java.math.BigDecimal;
//
//import org.osgeo.proj4j.CRSFactory;
//import org.osgeo.proj4j.CoordinateReferenceSystem;
//import org.osgeo.proj4j.CoordinateTransform;
//import org.osgeo.proj4j.CoordinateTransformFactory;
//import org.osgeo.proj4j.ProjCoordinate;
//
//public class Transform {
//	private static final double deltaY = 0.0000209302716314141434850171208381652832031250;
//	private static final double deltaX = 0.0029740986626194398922962136566638946533203125;
//			
//	public static void main(String[] args) {
//		ProjCoordinate trans = transform(156803.835377, 1571.10690442);
//		BigDecimal x = new BigDecimal(trans.x);
//		BigDecimal y = new BigDecimal(trans.y);
//		
//		BigDecimal dx = new BigDecimal(126.982314);
//		BigDecimal dy = new BigDecimal(37.571828);
//		
//		System.out.format("%s, %s", y.subtract(dy), x.subtract(dx));
//		System.out.format("\n%s, %s", trans.y - 37.571828, trans.x - 126.982314);
//		System.out.format("\n%s, %s", trans.y, trans.x);
//		System.out.format("\n%s, %s", trans.y+deltaY-0.000036, trans.x+deltaX-0.0000986);
//
//	}
//	
//	public static ProjCoordinate transform(Double x, Double y) {
//		CoordinateTransformFactory factory = new CoordinateTransformFactory();
//		CRSFactory crsFactory = new CRSFactory();
//		CoordinateReferenceSystem google = crsFactory.createFromParameters("EPSG:2097",
//				"+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs +towgs84=-115.80,474.99,674.11,1.16,-2.31,-1.63,6.43");
//		CoordinateReferenceSystem WGS84 = crsFactory.createFromParameters("WGS84", "+proj=longlat +datum=WGS84 +no_defs");
////		CoordinateReferenceSystem WGS84 = crsFactory.createFromParameters("EPSG:5179", "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs");
//		CoordinateTransform trans = factory.createTransform(google, WGS84);
//		ProjCoordinate p = new ProjCoordinate();
//		ProjCoordinate p2 = new ProjCoordinate();
//		p.x = x;
//		p.y = y;
//		return trans.transform(p,  p2);
//	}
//}
