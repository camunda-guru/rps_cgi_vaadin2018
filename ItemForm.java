package com.example.forms;


import com.example.models.Item;
import com.example.wisordemoproject.MyUI;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
//@Theme("wisordemoprojecttheme")
public class ItemForm extends FormLayout implements View{
	public static final String NAME="Item Form";
	private TextField itemCode=new TextField("Item Code");
	private TextField name=new TextField("Name");
	private TextField cost=new TextField("Cost");
	private DateField dop=new DateField();
	private Button savebtn =new Button("Save");
	private MyUI myUI;
	private Item item;
	private Binder<Item> binder=new Binder<>(Item.class);
	public ItemForm(MyUI ui)
	{
		binder.setBean(new Item());
		this.myUI=myUI;
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(savebtn);
		addComponents(itemCode, name, cost, dop,buttons);
			   
		savebtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
			   
		savebtn.setClickShortcut(KeyCode.ENTER);
		
		 binder.forField(itemCode)
		    .withConverter(
		      new StringToIntegerConverter("Must enter a number"))
		    .bind(Item::getItemCode, Item::setItemCode);
		 binder.forField(cost)
		    .withConverter(
		      new StringToLongConverter("Must enter a number"))
		    .bind(Item::getCost, Item::setCost);
		 
		 itemCode.addValueChangeListener(e->{
		    	//System.out.println(this.itemCode.getValue());
		    });
		    itemCode.setValueChangeMode(ValueChangeMode.LAZY);
		 
		 binder.bindInstanceFields(this);
		// System.out.println(this.binder.getBean().getName());
		 savebtn.addClickListener(e->save());
		 
	}
	private void save() {
		
		//System.out.println(this.itemCode.getValue());
		// TODO Auto-generated method stub
		//this.item=binder.getBean().getItemCode();
		System.out.println(binder.getBean().getItemCode());
	}
	
	

}
