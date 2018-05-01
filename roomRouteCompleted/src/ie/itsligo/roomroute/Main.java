package ie.itsligo.roomroute;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextField;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {

		static JTextField textField = null;
		static String qrCodeData1, qrCodeData2, qrCodeData3;
		static String filePath1, filePath2, filePath3; 
		
		static QR qr1,qr2,qr3;
		static Room room = new Room();
		static Directions directions = new Directions();
		static int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		static int minute = Calendar.getInstance().get(Calendar.MINUTE);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void main(String[] args) throws WriterException, IOException, NotFoundException {
			// Initial hardcoded data for test program 
			qrCodeData1 = "Day: Tuesday \nTime: 11.20 o'clock to 13.00 o'clock \nSubject: Software Engineering \nRoom: F1004 \n";
			qrCodeData2 = "Day: Wednesday \nTime: 7.00 o'clock to 9.00 o'clock \nSubject: Data coms \nRoom: E2003 \n";
			qrCodeData3 = "Day: Friday \nTime: 15.00 o'clock to 18.00 o'clock \nSubject: Embedded Programming \nRoom: z204 \n";
			
			filePath1 = "NormalUseCaseQRCode.png";
			filePath2 = "SecondUseCaseQRCode.png";
			filePath3 = "ErrorCaseQRCode.png";
			
			qr1 = new QR(qrCodeData1, filePath1);
			qr2 = new QR(qrCodeData2, filePath2);
			qr3 = new QR(qrCodeData3, filePath3);
			
			Map hintMap = new HashMap();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			// read the barcode
			String QRdata1 = qrCodeData1; //qr1.readQRCode(filePath1, hintMap);
			String QRdata2 = qrCodeData2; //qr2.readQRCode(filePath2, hintMap);
			String QRdata3 = qrCodeData3; //qr3.readQRCode(filePath3, hintMap);
			System.out.println("The barcode 1 reads : \n" + QRdata1 + "\n");
			System.out.println("The barcode 2 reads : \n" + QRdata2 + "\n");
			System.out.println("The barcode 3 reads : \n" + QRdata3 + "\n");
					
			// Find the room
			String theRoom1 = room.get(QRdata1);
			System.out.println("The first room is [" + theRoom1 + "]");
			
			String theRoom2 = room.get(QRdata2);
			System.out.println("The second room is [" + theRoom2 + "]");
			
			String theRoom3 = room.get(QRdata3);
			System.out.println("The third room is [" + theRoom3 + "]");
			
			// create the QR barcode
			qr1.createQRCode(qrCodeData1 + getQRdirection(QRdata1, theRoom1), filePath1, hintMap, 500, 500);
			qr2.createQRCode(qrCodeData2 + getQRdirection(QRdata2, theRoom2), filePath2, hintMap, 500, 500);
			qr3.createQRCode(qrCodeData3 + getQRdirection(QRdata3, theRoom3), filePath3, hintMap, 500, 500);
			System.out.println("QR Code images created successfully!\n");
			
			// get directions for the first QR code
			if (directions.validate(theRoom1) == false) {
				System.out.println("\nThe directions to this room are unknown");
			}
			else if(getQRhour(QRdata1)<hour) {
				System.out.println("\nThe course you have scanned has already passed. It began at "+ room.getHour(QRdata1) + ":" + room.getMinute(QRdata1));	
			}
			else if(getQRhour(QRdata1)==hour) {
				if (getQRminute(QRdata1)<minute)
					System.out.println("\nThe course you have scanned has already passed. It began at "+ room.getHour(QRdata1) + ":" + room.getMinute(QRdata1));
			}
			else {
				System.out.println("\nDIRECTIONS");
				System.out.println(directions.toBuilding());
				System.out.println(directions.toFloor());
				System.out.println(directions.toLocation());
				directions.playMusic(directions.getBuildingSoundFile());
				directions.playMusic1(directions.getFloorSoundFile());
				directions.playMusic2(directions.getLocaationOnFloor());
		    }
			
			// get directions for the second QR code
			if (directions.validate(theRoom2) == false) {
				System.out.println("\nThe directions to this room are unknown");
			}
			else if(getQRhour(QRdata2)<hour) {
				System.out.println("\nThe course you have scanned has already passed. It began at "+ room.getHour(QRdata2) + ":" + room.getMinute(QRdata2));	
			}
			else if(getQRhour(QRdata2)==hour) {
				if (getQRminute(QRdata2)<minute)
					System.out.println("\nThe course you have scanned has already passed. It began at "+ room.getHour(QRdata2) + ":" + room.getMinute(QRdata2));
			}
			else {
				System.out.println("\nDIRECTIONS");
				System.out.println(directions.toBuilding());
				System.out.println(directions.toFloor());
				System.out.println(directions.toLocation());
				directions.playMusic(directions.getBuildingSoundFile());
				directions.playMusic1(directions.getFloorSoundFile());
				directions.playMusic2(directions.getLocaationOnFloor());
		    }
			
			// get directions for the third QR code
			if (directions.validate(theRoom3) == false) {
				System.out.println("\nThe directions to this room are unknown");
			}
			else if(getQRhour(QRdata3)<hour) {
				System.out.println("\nThe course you have scanned has already passed. It began at "+ room.getHour(QRdata3) + ":" + room.getMinute(QRdata3));	
			}
			else if(getQRhour(QRdata3)==hour) {
				if (getQRminute(QRdata3)<minute)
					System.out.println("\nThe course you have scanned has already passed. It began at "+ room.getHour(QRdata3) + ":" + room.getMinute(QRdata3));
			}
			else {
				System.out.println("\nDIRECTIONS");
				System.out.println(directions.toBuilding());
				System.out.println(directions.toFloor());
				System.out.println(directions.toLocation());
				directions.playMusic(directions.getBuildingSoundFile());
				directions.playMusic1(directions.getFloorSoundFile());
				directions.playMusic2(directions.getLocaationOnFloor());
		    }
		
		/**
	     * Send e-mail with attachments(QR codes)
	     */
		Scanner sc = new Scanner(System.in);
		System.out.println("\nPlease enter your email to receive your QR codes:");
		String mailTo = sc.nextLine();
		sc.close();
	    
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "qrcodereceiver@gmail.com";
        String password = "mdpqrcode8";
 
        // message info
        //String mailTo = "qrcodereceiver@gmail.com";
        String subject = "New QR code";
        String message = "Scan the QR code to get informations for the next course!";
 
        // attachments
        String[] attachFiles = new String[3];
        attachFiles[0] = "NormalUseCaseQRCode.png";
        attachFiles[1] = "SecondUseCaseQRCode.png";
        attachFiles[2] = "ErrorCaseQRCode.png";
 
        try {
        	EmailAttachmentSender.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Email not valid, could not send email.");
            //ex.printStackTrace();
        }
        new WebcamQRCode();
	}
		
		static int getQRhour(String QRdata) {
			// Find hour in the data of the QRcode
			String theHour = room.getHour(QRdata);
			int QRhour = Integer.parseInt(theHour);
			return QRhour;
		}
		static int getQRminute(String QRdata) {
			// Find minute in the data of the QR code
			String theMinute = room.getMinute(QRdata);
			int QRminute = Integer.parseInt(theMinute);
			return QRminute;
		}
		
		static String getQRdirection(String QRdata, String theRoom) {
			// get directions for the QR code
			if (directions.validate(theRoom) == false) {
				return "The directions to this room are unknown";
			}
			else if(getQRhour(QRdata)<hour) {
				return "The course you have scanned has already passed. It began at "+ room.getHour(QRdata) + ":" + room.getMinute(QRdata);	
			}
			else if(getQRhour(QRdata)==hour) {
				if (getQRminute(QRdata)<minute)
					return "The course you have scanned has already passed. It began at "+ room.getHour(QRdata) + ":" + room.getMinute(QRdata);
			}
			else {
				return "\nDIRECTIONS\n" + directions.toBuilding() + "\n" + directions.toFloor() + "\n" + directions.toLocation();
		    }
			return null;
		}

}
