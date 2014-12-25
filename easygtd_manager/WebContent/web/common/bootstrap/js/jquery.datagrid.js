/*
Description: $.fn.datagrid
Author: Kris Zhang
require: 
  string.format.js
*/

(function($) {

  $.fn.datagrid = function(method, options) {

    var self          = this
      , selectedClass = "success"
      , $this         = $(this)
      ;

    var bindRows = function($rows) {
      var conf          = $this.data("config")
        , selectChange  = conf.selectChange
        , singleSelect  = conf.singleSelect
        , edit          = conf.edit
        ;

      var selectHandler = function(e) {
        var $row              = $(this)
          , hasSelectedClass  = $row.hasClass(selectedClass)
          , idx               = $("tbody tr", $this).index($row)
          , row               = $this.data("rows")[idx] || {}
          ;

        //rows may added dynamiclly
        singleSelect && $("tbody tr", $this).removeClass(selectedClass);
        $row.toggleClass(selectedClass);

        //API selectChange: function( selected, rowIndex, rowData )
        selectChange && selectChange(!hasSelectedClass, idx, row, $row);
      };
      (selectChange || typeof singleSelect != "undefined") && $rows.click(selectHandler);


      var editHandler = function(e) {
        var $input  = $(this)
          , $row    = $input.closest("tr")
          , idx     = $("tbody tr", $this).index($row)
          , row     = $this.data("rows")[idx] || {}
          , name    = $input.attr("name")
          ;

        name && (row[name] = $input.val());
      }
      edit && $rows.find("input").keyup(editHandler);
    };

    var getRow = function(columns, row, conf) {
      var trow = "<tr>";

      for (var j = 0, m = columns[0].length; j < m; j++) {
        var column = columns[0][j]
          , format = column.formatter
          , field  = column.field
          , tip    = column.tip
          , value  = row[field]
          , maxlength = column.maxlength
          , readonly  = column.readonly
          ;

        typeof value == "undefined" && (value = "");

        if (conf.edit) {
          maxlength = maxlength
            ? ' maxlength="{0}"'.format(column.maxlength)
            : '';

          readonly  = readonly ? ' readonly="readonly"' : '';

          value
            = '<input name="{0}" value="{1}" class="form-control"{2}{3}/>'.format(
                column.field
              , value
              , maxlength
              , readonly
            );
        }

        //if it has 'formatter' attribute override the content
        value = format ? format(row[field], row) : value;
        trow = trow + "<td>" + value + "</td>";
      };
      trow += "</tr>";
      return trow;
    };

    var getData = function(edit) {
      if (!options) return;

      var config  = $this.data("config") || {}
        , columns = config.columns
        , rows    = options.rows || options;

      var body = "<tbody>";
      if (rows) {
        for (var i = 0, l = rows.length; i < l; i++) {
          body += getRow(columns, rows[i], config);
        }
      }
      body += "</tbody>";

      $("tbody", $this).remove();
      $this
        .data("rows", rows)
        .append(body);

      //add "edit" class if it's edit mode.
      config.edit && $this.addClass("edit");
      //rebind events
      bindRows($("tbody tr", $this));
    };

    //handle: $().datagrid({column: [[]]})
    if (method && method.constructor == Object) {
      var columns = method.columns;
 
      if (columns) {
        $("thead", $this).size() < 1
          && $this.append("<thead></thead>");

        var header = "<tr>";
        //method.del && (header += "<td></td>");
        for (var i = 0, l = columns[0].length; i < l; i++) {
          var col = columns[0][i];
          header += '<th>' + (col.title || "") + '</th>';
        }
        header += "</tr>";

        $this.data("config",  method);
        $("thead", $this).html(header);
      }
    }

    //handle: $().datagrid("loadData", {rows: []}) or $().data("loadData", [])
    if (method == "loadData") getData();

    if (method == "getData") {
      return $this.data("rows");
    }

    if (method == "getConfig") {
      return $this.data("config");
    }

    if (method == "getColumns") {
      return $this.data("config").columns;
    }

    if (method == "unselectRow") {
      typeof options != "undefined"
        ? $("tbody tr", $this).eq(options).removeClass(selectedClass)
        : $("tbody tr", $this).removeClass(selectedClass);
    }

    if (method == "updateRow") {
      var idx     = options.index
        , conf    = $this.data("config")
        , rows    = $this.data("rows")
        , row     = options.row
        , columns = conf.columns
        ;

      if (rows) {
        row = $.extend(rows[idx], row);
        $this.data("rows", rows);
      }

      var $row = $(getRow(columns, row, conf));

      $("tbody tr", $this).eq(idx)
        .after($row)
        .remove();

      bindRows($row);
    }

    if (method == "getSelections") {
      var rows    = $this.data("rows")
        , selRows = [];

      $("tbody tr", $this).each(function(idx) {
        $(this).hasClass(selectedClass) && selRows.push(rows[idx]);
      });

      return selRows;
    }

    if (method == "insertRow") {
      var idx   = options.index || 0
        , row   = options.row
        , conf  = $this.data("config")
        , rows  = $this.data("rows") || []
        ;

      if (!conf || !row) return $this;

      var $rows  = $("tbody tr", $this)
        , $row   = $(getRow(conf.columns, row, conf))
        , $tar   = $rows.eq(idx)
        ;

      bindRows($row);
      $tar.size() ? $tar.before($row) : $("tbody", $this).append($row);
      rows.splice(idx, 0, row);
    }

    if (method == "deleteRow") {
      if (options > -1) {
        $("tbody tr", $this).eq(options).remove();
        var rows = $this.data("rows");
        rows.splice(options, 1);
      }
    }

    return self;
  };


})(jQuery);