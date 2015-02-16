///copyer_add("file_name")
/*----------------------------------------------
DESC: 添加需要复制的文件，到表单中
RESU:  return : true or false 
------------------------------------------------
--  copy_helper 
---------------------------------------
--  复制指定 include files (assets)下的文件 
--  到 /sdcard/Android/data/$(应用的包路径)
--    
--  复制保持原目录结构
--  同名 不覆盖/不执行
*/

var _file_add  = argument0 ; 
// 检测
    // 检测 include files( assets )
    if !file_exists( _file_add ) return false ;
    // 检测 表单
var _file = file_text_open_read( "assets.lst" ) ;
    while( !file_text_eof( _file ) )
        {
        var _file_name = file_text_readln(_file) ;
        if _file_name == _file_add
            {
            return true ;  // 文件名 已存在 表单 中
            }
        }
file_text_close(_file)
// 文件名 写入 表单
_file = file_text_open_write( "assets.lst" ) ;
file_text_write_string(_file, _file_add);
file_text_writeln(_file)
file_text_close(_file)
return true ; 
