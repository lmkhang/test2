package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import Configs.Setting;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

/**
 * @author Christopher Deckers
 */
public class browser {

	public static void createContent(String title, String url) {
		final JFrame frame = new JFrame(title);
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		webBrowserPanel.setBorder(BorderFactory.createTitledBorder(title));
		final JWebBrowser webBrowser = new JWebBrowser();
		webBrowser.navigate(url);
		webBrowser.setJavascriptEnabled(true);
		webBrowser.setMenuBarVisible(false);
		webBrowser.setBarsVisible(false);
		webBrowser.setFocusable(false);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		contentPane.add(webBrowserPanel, BorderLayout.CENTER);
		// Create an additional bar allowing to show/hide the menu bar of the
		// web browser.
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
		final JProgressBar loading = new JProgressBar(0);
		webBrowser.addWebBrowserListener(new WebBrowserListener() {

			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void titleChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void statusChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void locationChanging(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void locationChanged(WebBrowserNavigationEvent wbe) {
				// TODO Auto-generated method stub
			}

			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void loadingProgressChanged(WebBrowserEvent wbe) {
				// TODO Auto-generated method stub
				JWebBrowser wb = wbe.getWebBrowser();
				int progress = wb.getLoadingProgress();
				loading.setValue(progress);
				loading.setStringPainted(true);
				loading.setString(progress + "%");
			}

			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		buttonPanel.add(loading);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		
		frame.getContentPane().add(contentPane, BorderLayout.CENTER);
		frame.setSize(800, 500);
		frame.setLocation(300, 50);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}