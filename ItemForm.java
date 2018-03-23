package com.example.forms;


import com.example.models.Item;
import com.example.wisordemoproject.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class ItemForm extends FormLayout {
	
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
		
		 binder.bindInstanceFields(this);
		 savebtn.addClickListener(e->save());
		 
	}
	private void save() {
		// TODO Auto-generated method stub
		System.out.println(this.item);
	}
	
	

}
