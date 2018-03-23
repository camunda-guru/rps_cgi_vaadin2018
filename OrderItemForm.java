package com.example.forms;


import com.example.dao.ItemDao;
import com.example.models.Item;
import com.example.models.OrderItem;
import com.example.wisordemoproject.MyUI;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class OrderItemForm extends FormLayout implements View{
	public static final String NAME="Order Item Form";
	private NativeSelect<String> nameList=new NativeSelect<String>();
	private TextField qty=new TextField();
	private TextField amount=new TextField();
	private Button savebtn =new Button("Save");
	private MyUI myUI;
	private Binder<OrderItem> binder=new Binder<>(OrderItem.class);
	
	public OrderItemForm(MyUI ui)
	{
		
		binder.setBean(new OrderItem());
		
		nameList.setItems(ItemDao.getItemList().values());
		this.myUI=ui;
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(savebtn);
		addComponents(nameList, qty, amount,buttons);
			   
		savebtn.setStyleName(ValoTheme.BUTTON_PRIMARY);
			   
		savebtn.setClickShortcut(KeyCode.ENTER);
		
		 binder.forField(qty)
		    .withConverter(
		      new StringToIntegerConverter("Must enter a number"))
		    .bind(OrderItem::getQty, OrderItem::setQty);
		 binder.forField(amount)
		    .withConverter(
		      new StringToIntegerConverter("Must enter a number"))
		    .bind(OrderItem::getAmount, OrderItem::setAmount);
		 
		 //itemCode.addValueChangeListener(e->{
		    	//System.out.println(this.itemCode.getValue());
		    //});
		   // itemCode.setValueChangeMode(ValueChangeMode.LAZY);
		 
		 binder.bindInstanceFields(this);
		// System.out.println(this.binder.getBean().getName());
		 savebtn.addClickListener(e->save());
		 
		
	}

	private void save() {
		// TODO Auto-generated method stub
		System.out.println(this.binder.getBean().getQty());
	}

}
