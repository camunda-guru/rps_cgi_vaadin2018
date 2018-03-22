package com.example.wisordemoproject;

import javax.servlet.annotation.WebServlet;


import com.example.views.ASRView;
import com.example.views.SOWAView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	Image logo = new Image("",
    		    new ClassResource("bell.png"));
    	logo.setWidth("100px");
    	logo.setHeight("100px");
        Label menuHeading =new Label("CSG View");
        menuHeading.addStyleName(ValoTheme.MENU_TITLE);
        
        Button asr=new Button("ASR");
      
        asr.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        
        Button sowa=new Button("SOWA");
        sowa.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        Button csr=new Button("CSR");
        csr.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        Button provisioning=new Button("PROVISIONING");
        provisioning.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        Button tables=new Button("TABLES");
        tables.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        Button reports=new Button("REPORTS");
        reports.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        Button userprefs=new Button("USERPREFS");
        userprefs.addStyleNames(ValoTheme.BUTTON_LINK,ValoTheme.MENU_ITEM);
        Image applnLogo = new Image("",
    		    new ClassResource("owl.jpg"));
        applnLogo.setWidth("100px");
        applnLogo.setHeight("100px");
        Button close=new Button("CLOSE");
        close.addStyleNames(ValoTheme.BUTTON_DANGER);
        VerticalLayout vlayout=new VerticalLayout();
        CssLayout menulayout=new CssLayout();        
        menulayout.addComponents(menuHeading,asr,sowa,csr,provisioning,tables,reports,userprefs);
        vlayout.addComponents(logo,menulayout,applnLogo,close);
        CssLayout container=new CssLayout();
        HorizontalLayout page=new HorizontalLayout();
        page.addComponents(vlayout,container);
        
        Navigator navigator = new Navigator(this,container);
        navigator.addView(ASRView.NAME, new ASRView());
        navigator.addView(SOWAView.NAME,new SOWAView());
        navigator.addView("", new ASRView());
        
        navigator.navigateTo(ASRView.NAME);
        asr.addClickListener(event->{
        	
        	
        	navigator.navigateTo(ASRView.NAME);
        	
        });
 sowa.addClickListener(event->{
        	
        	
        	navigator.navigateTo(SOWAView.NAME);
        	
        });
        
        
        setContent(page);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
