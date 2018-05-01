package ie.itsligo.roomroute;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class WebcamQRCode extends JFrame implements Runnable, ThreadFactory {

	private static final long serialVersionUID = 6441489157408381878L;

	private Executor executor = Executors.newSingleThreadExecutor(this);

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private JTextArea textarea = null;
	static Room room = new Room();
	static Directions directions = new Directions();
	
	public WebcamQRCode() {
		super();

		setLayout(new FlowLayout());
		setTitle("Read QR / Bar Code With Webcam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension size = WebcamResolution.QVGA.getSize();
		Dimension customSize = new Dimension(600,size.height);
		
		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);

		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);

		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setPreferredSize(customSize);

		add(panel);
		add(textarea);

		pack();
		setVisible(true);

		executor.execute(this);
	}

	@Override
	public void run() {

		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) {

				if ((image = webcam.getImage()) == null) {
					continue;
				}

				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}
			if (result != null) {

				//Display the text of the QR code when scanning with the webcam 
				textarea.setText(result.getText());
				//Play a Sound if the webcam is scanning a QRcode with sound
				String theRoom = room.get(result.getText());
				if (directions.validate(theRoom) == false) {
					System.out.println("\nThe directions to this room are unknown");
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
			}

		} while (true);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}
}