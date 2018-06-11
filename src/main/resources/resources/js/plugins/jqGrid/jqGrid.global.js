jQuery.extend(jQuery.jgrid.defaults, {
	altRows : true,
	sortable : false,
	styleUI : 'Bootstrap',
    rowNum: 20,
    autowidth:true,
	viewrecords: true,
	rownumbers:true,
    //multiselect: true,
	prmNames : {
		page : "page",
		rows : "rows",
		sort : "sidx",
		order : "sord",
		search : "_search",
		nd : "nd",
		id : "id",
		oper : "oper",
		editoper : "edit",
		addoper : "add",
		deloper : "del",
		subgridid : "id",
		npage : null,
		totalrows : "totalrows"
	},
	rowList:[10,20,50,100]
});